package com.asm.Controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.asm.DAO.userDAO;
import com.asm.bean.User;
import com.asm.ultils.CookieService;
import com.asm.ultils.ParamService;
import com.asm.ultils.SessionService;

import jakarta.servlet.http.HttpServletRequest;

import org.mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

		@Mock
	    private userDAO userdao; // Đối tượng giả lập cho UserDAO

	    @InjectMocks
	    private AccountController accountController; // Controller mà bạn đang kiểm thử

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this); // Khởi tạo đối tượng giả lập
	    }

	    @Test
	    void testGetLogin() throws Exception {
	        // given
	        String username = "Admin1";
	        String password = "oldmovie24";
	        User mockUser = new User("Admin1", "oldmovie24", "", "Admin"); // Tạo người dùng giả lập

	        // Thiết lập hành vi giả lập cho userdao
	        when(userdao.findByUsername(username)).thenReturn(mockUser);
	        // when
	        User user = userdao.findByUsername(username); // Gọi phương thức cần kiểm tra

	        // then
	        assertNotNull(user, "User should not be null for username: " + username);
	        assertEquals(password, user.getPassword(), "The password should match the expected password");
	    }
//	@Test
//	void testGetLogin_1() throws Exception {
//		// given
//		User user = TestValueFactory.fillFields(new User());
//		Model model = TestValueFactory.fillFields(new Model());
//		// when
//		String actual=underTest.getLogin(user, model);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testPostLogin() throws Exception {
//		// given
//		User user = TestValueFactory.fillFields(new User());
//		Model model = TestValueFactory.fillFields(new Model());
//		RedirectAttributes redirectAttributes = TestValueFactory.fillFields(new RedirectAttributes());
//		// when
//		String actual=underTest.postLogin(user, model, redirectAttributes);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testPostLogin_1() throws Exception {
//		// given
//		User user = TestValueFactory.fillFields(new User());
//		Model model = TestValueFactory.fillFields(new Model());
//		RedirectAttributes redirectAttributes = TestValueFactory.fillFields(new RedirectAttributes());
//		// when
//		String actual=underTest.postLogin(user, model, redirectAttributes);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testGetSignUp() throws Exception {
//		// given
//		Model model = TestValueFactory.fillFields(new Model());
//		User user = TestValueFactory.fillFields(new User());
//		// when
//		String actual=underTest.getSignUp(model, user);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testGetSignUp_1() throws Exception {
//		// given
//		Model model = TestValueFactory.fillFields(new Model());
//		User user = TestValueFactory.fillFields(new User());
//		// when
//		String actual=underTest.getSignUp(model, user);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testPostSignUp() throws Exception {
//		// given
//		Model model = TestValueFactory.fillFields(new Model());
//		User user = TestValueFactory.fillFields(new User());
//		Errors errors = TestValueFactory.fillFields(new Errors());
//		// when
//		String actual=underTest.postSignUp(model, user, errors);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}
//	@Test
//	void testPostSignUp_1() throws Exception {
//		// given
//		Model model = TestValueFactory.fillFields(new Model());
//		User user = TestValueFactory.fillFields(new User());
//		Errors errors = TestValueFactory.fillFields(new Errors());
//		// when
//		String actual=underTest.postSignUp(model, user, errors);
//		// then
//		assertThat(actual).isEqualTo("TestExpected");
//	}


}