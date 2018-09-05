define(["vue","vuex","./module/app","./module/user"], function(Vue,Vuex,app,user) {
    Vue.use(Vuex)
   return new Vuex.Store({
       state: {
           //
       },
       mutations: {
           //
       },
       actions: {
           //
       },
       modules: {
           app,user
       }
   });
});