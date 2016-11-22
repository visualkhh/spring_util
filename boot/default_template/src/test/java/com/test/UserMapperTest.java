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
//            User user = userMapper.readUser("cusonar");
//            assertThat("cusonar", is(user.getUsername()));
//            assertThat("YCU", is(user.getName()));
//            assertThat("1234", is(user.getPassword()));
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
