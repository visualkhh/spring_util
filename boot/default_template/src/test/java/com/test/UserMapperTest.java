//package com.test;
//
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//public class UserMapperTest {
//    @RunWith(SpringJUnit4ClassRunner.class)
//    @SpringApplicationConfiguration(classes = ExampleApplication.class)
//    @WebAppConfiguration
//    public class UserMapperTest {
//
//        @Autowired UserMapper userMapper;
//
//        @Test
//        public void readUserTest() {
//            User admin = userMapper.readUser("cusonar");
//            assertThat("cusonar", is(admin.getUsername()));
//            assertThat("YCU", is(admin.getName()));
//            assertThat("1234", is(admin.getPassword()));
//        }
//
//        @Test
//        public void readAuthorityTest() {
//            List<String> authorities = userMapper.readAuthority("cusonar");
//            assertThat(authorities, hasItems("ADMIN", "USER"));
//
//            authorities= userMapper.readAuthority("abc");
//            assertThat(authorities, hasItem("USER"));
//        }
//}
