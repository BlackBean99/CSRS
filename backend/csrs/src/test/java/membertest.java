import lombok.Setter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.PreparedStatement;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({
//        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
//        "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberTests {

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder pwencoder;

    @Setter(onMethod_ = @Autowired)
    private DataSource ds;

    // 유저 추가
    @Test
    public void testInsertMember() {

        String sql = "insert into tbl_member(userid, userpw, username) values (?,?,?)";

        for(int i = 0; i < 10; i++) {

            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = ds.getConnection();
                pstmt = con.prepareStatement(sql);

                pstmt.setString(2, pwencoder.encode("pw" + i));

                if(i <8) {

                    pstmt.setString(1, "user"+i);
                    pstmt.setString(3,"사용자"+i);

                }else if (i <9) {

                    pstmt.setString(1, "manager"+i);
                    pstmt.setString(3,"매니저"+i);

                }else {

                    pstmt.setString(1, "admin"+i);
                    pstmt.setString(3,"관리자"+i);

                }

                pstmt.executeUpdate();

            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
                if(con != null) { try { con.close();  } catch(Exception e) {} }

            }
        }
    }

    // 권한 추가
    @Test
    public void testInsertAuth() {


        String sql = "insert into tbl_member_auth (userid, auth) values (?,?)";

        for(int i = 0; i < 10; i++) {

            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = ds.getConnection();
                pstmt = con.prepareStatement(sql);


                if(i <8) {

                    pstmt.setString(1, "user"+i);
                    pstmt.setString(2,"ROLE_USER");

                }else if (i <9) {

                    pstmt.setString(1, "manager"+i);
                    pstmt.setString(2,"ROLE_MEMBER");

                }else {

                    pstmt.setString(1, "admin"+i);
                    pstmt.setString(2,"ROLE_ADMIN");

                }

                pstmt.executeUpdate();

            }catch(Exception e) {
                e.printStackTrace();
            }finally {
                if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
                if(con != null) { try { con.close();  } catch(Exception e) {} }

            }
        }//end for
    }
}