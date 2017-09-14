

```java
/**
 * test
 * @author airlenet
 * @version 2017-09-14
 */
@Table(name = "test_test")
@Entity
@Getter
@Setter
public class TestEntity extends DataEntity<AdminUserEntity, Long> {
    /** 名称 */
    @Column(nullable = false, length = 100)
    private String name;
}

```