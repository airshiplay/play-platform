package com.airshiplay.play.obd.ecar;

import com.airshiplay.play.http.PlayHttpClient;
import com.airshiplay.play.obd.entity.ObdCfgCarEntity;
import com.airshiplay.play.obd.entity.QObdCfgCarEntity;
import com.airshiplay.play.obd.service.ObdCfgCarEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by lig on 17/1/23.
 */
@Service
public class EcarApiTest {
    static ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    ObdCfgCarEntityService obdCfgCarEntityService;

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    }

    public void file() throws IOException {


        EcarApi ecarApi = PlayHttpClient.getApi("http://bu.wedrive.com.cn/", EcarApi.class);

        Iterable<ObdCfgCarEntity> all = obdCfgCarEntityService.findAll(QObdCfgCarEntity.obdCfgCarEntity.type.eq(ObdCfgCarEntity.CfgCarType.brand));

        Iterator<ObdCfgCarEntity> iterator = all.iterator();
        while (iterator.hasNext()){
            ObdCfgCarEntity entity=  iterator.next();

            Response<ResponseBody> response = ecarApi.getFile(entity.getCode()+".png").execute();

            byte[] bytes = response.body().bytes();
            File file=  new File("img/"+entity.getCode()+".png");
            file.getParentFile().mkdirs();
            FileOutputStream fileOuputStream=new FileOutputStream(file);
            fileOuputStream.write(bytes);
        }

    }

    public void main() throws IOException, InterruptedException {
        EcarApi ecarApi = PlayHttpClient.getApi("http://m.wedrive.com.cn/", EcarApi.class);
        //"data=%7B%22CODE%22%3A%22carTree%22%2C%22PARAMS%22%3A%7B%22LEVEL%22%3A%223%22%2C%22CAR_ID%22%3A%22dazhong-baolailiangxiang%22%2C%22APPVER%22%3A%22%22%2C%22TOKEN%22%3A%227bc3619869c34e1891aab22ecc1bbb27%22%2C%22CUST_ID%22%3A%2213655177723%22%2C%22UA%22%3A%22other%22%7D%7D"


        //{"CODE":"carTree","PARAMS":{"LEVEL":"1","CAR_ID":"","APPVER":"","TOKEN":"7bc3619869c34e1891aab22ecc1bbb27","CUST_ID":"13655177723","UA":"other"}}
        //{"CODE":"carTree","PARAMS":{"LEVEL":"2","CAR_ID":"dazhong","APPVER":"","TOKEN":"7bc3619869c34e1891aab22ecc1bbb27","CUST_ID":"13655177723","UA":"other"}}
        //{"CODE":"carTree","PARAMS":{"LEVEL":"3","CAR_ID":"fengtian-huaguan","APPVER":"","TOKEN":"7bc3619869c34e1891aab22ecc1bbb27","CUST_ID":"13655177723","UA":"other"}}
//"{\"CODE\":\"carTree\",\"PARAMS\":{\"LEVEL\":\"3\",\"CAR_ID\":\"\",\"APPVER\":\"\",\"TOKEN\":\"7bc3619869c34e1891aab22ecc1bbb27\",\"CUST_ID\":\"13655177723\",\"UA\":\"other\"}}"


        List<Map<String, Object>> list = getCarLevel(ecarApi, "1", "");
        Thread.sleep(2000);

        for (Map map : list) {
            map.get("CAR_ID");
            map.get("NODE_TEXT");

            ObdCfgCarEntity obdCfgCarEntityBrand = obdCfgCarEntityService.findOne(QObdCfgCarEntity.obdCfgCarEntity.code.eq(map.get("CAR_ID").toString())
                    .and(QObdCfgCarEntity.obdCfgCarEntity.type.eq(ObdCfgCarEntity.CfgCarType.brand)));
            if (obdCfgCarEntityBrand == null) {
                obdCfgCarEntityBrand = new ObdCfgCarEntity();
                obdCfgCarEntityBrand.setCode(map.get("CAR_ID").toString());
                obdCfgCarEntityBrand.setName(map.get("NODE_TEXT").toString());
                obdCfgCarEntityBrand.setType(ObdCfgCarEntity.CfgCarType.brand);
                obdCfgCarEntityService.save(obdCfgCarEntityBrand);
            }


            List<Map<String, Object>> list2 = getCarLevel(ecarApi, "2", map.get("CAR_ID").toString());
            Thread.sleep(2000);
            //map.put("childs", list2);
            for (Map map2 : list2) {
                map2.get("CAR_ID");
                map2.get("NODE_TEXT");

                ObdCfgCarEntity obdCfgCarEntitySeries = obdCfgCarEntityService.findOne(QObdCfgCarEntity.obdCfgCarEntity.code.eq(map2.get("CAR_ID").toString())
                        .and(QObdCfgCarEntity.obdCfgCarEntity.type.eq(ObdCfgCarEntity.CfgCarType.series)));

                if (obdCfgCarEntitySeries == null) {
                    obdCfgCarEntitySeries = new ObdCfgCarEntity();
                    obdCfgCarEntitySeries.setCode(map2.get("CAR_ID").toString());
                    obdCfgCarEntitySeries.setName(map2.get("NODE_TEXT").toString());
                    obdCfgCarEntitySeries.setOemName(map2.get("OEM_NAME").toString());
                    obdCfgCarEntitySeries.setType(ObdCfgCarEntity.CfgCarType.series);
                    obdCfgCarEntitySeries.setParent(obdCfgCarEntityBrand);
                    obdCfgCarEntityService.save(obdCfgCarEntitySeries);
                }

                List<Map<String, Object>> list3 = getCarLevel(ecarApi, "3", map2.get("CAR_ID").toString());
                Thread.sleep(2000);
                for (Map map3 : list3) {
                    ObdCfgCarEntity obdCfgCarEntityType = obdCfgCarEntityService.findOne(QObdCfgCarEntity.obdCfgCarEntity.code.eq(map3.get("CAR_ID").toString())
                            .and(QObdCfgCarEntity.obdCfgCarEntity.type.eq(ObdCfgCarEntity.CfgCarType.type)));

                    if (obdCfgCarEntityType == null) {
                        obdCfgCarEntityType = new ObdCfgCarEntity();
                        obdCfgCarEntityType.setCode(map3.get("CAR_ID").toString());
                        obdCfgCarEntityType.setName(map3.get("NODE_TEXT").toString());
                        obdCfgCarEntityType.setParent(obdCfgCarEntitySeries);
                        obdCfgCarEntityType.setType(ObdCfgCarEntity.CfgCarType.type);
                        obdCfgCarEntityService.save(obdCfgCarEntityType);
                    }


                    List<Map> list4 = (List<Map>) map3.get("childs");
                    for (Map map4 : list4) {

                        ObdCfgCarEntity obdCfgCarEntityDetail = obdCfgCarEntityService.findOne(QObdCfgCarEntity.obdCfgCarEntity.code.eq(map4.get("CAR_ID").toString())
                                .and(QObdCfgCarEntity.obdCfgCarEntity.type.eq(ObdCfgCarEntity.CfgCarType.detail)));

                        if (obdCfgCarEntityDetail == null) {
                            obdCfgCarEntityDetail = new ObdCfgCarEntity();
                            obdCfgCarEntityDetail.setCode(map4.get("CAR_ID").toString());
                            obdCfgCarEntityDetail.setName(map4.get("NODE_TEXT").toString());
                            obdCfgCarEntityDetail.setParent(obdCfgCarEntityType);
                            obdCfgCarEntityDetail.setType(ObdCfgCarEntity.CfgCarType.detail);
                            obdCfgCarEntityService.save(obdCfgCarEntityDetail);
                        }


                    }
                }

            }
        }
    }

    public static List<Map<String, Object>> getCarLevel(EcarApi ecarApi, String level, String cardId) throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("CODE", "carTree");
        Map<String, Object> params = new HashMap<>();
        root.put("PARAMS", params);
        params.put("LEVEL", level);
        params.put("CAR_ID", cardId);
        params.put("APPVER", "");
        params.put("TOKEN", "7bc3619869c34e1891aab22ecc1bbb27");
        params.put("CUST_ID", "13655177723");
        params.put("UA", "other");
        String data = objectMapper.writeValueAsString(root);
        Response<Map<String, Object>> response = ecarApi.getBrand(data).execute();
        System.out.println(response.code());
        Map<String, Object> body = response.body();
        List<Map<String, Object>> result = new ArrayList<>();

        if ("1".equals(level)) {
            Map<String, Object> details = (Map<String, Object>) body.get("details");
            Iterator<Map.Entry<String, Object>> iterator = details.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                List<Map<String, Object>> list = (List<Map<String, Object>>) entry.getValue();
                result.addAll(list);
            }
        } else if ("2".equals(level)) {
            List<Map<String, Object>> details = (List<Map<String, Object>>) body.get("details");
            result.addAll(details);
        } else if ("3".equals(level)) {
            List<Map<String, Object>> details = (List<Map<String, Object>>) body.get("details");

            for (Map<String, Object> map : details) {
                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                Map m = new HashMap<>();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    if (entry.getValue() instanceof List) {
                        List<Map<String, Object>> childMap = (List<Map<String, Object>>) entry.getValue();
                        m.put("childs", childMap);
                    } else {
                        m.put(entry.getKey(), entry.getValue());
                    }
                }
                result.add(m);

            }
        }
        System.out.println(objectMapper.writeValueAsString(result));
        return result;


    }


}
