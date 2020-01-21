package edu.bit.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.bit.board.vo.UserVO;

// Interceptor 만들기! : DispatcherServlet 객체 생성 이후 생성된다.
// request와 response를 낚아채는 것은 사실 해킹... 맘대로 조작 가능... 와우넴
public class BoardInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		System.out.println("preHandle 실행");
		
		// session 객체를 가져옴
		HttpSession session = request.getSession();
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
		UserVO member = (UserVO) session.getAttribute("member");
		
		if(member==null) {
			// 로그인이 안 되어 있는 상태이므로 로그인 폼으로 다시 돌려보냄(redirect)
			response.sendRedirect(request.getContextPath()); // contextpath로 보내버림
			return false; // 더 이상 컨트롤로 요청으로 가지 않도록 false로 반환함
		}
		// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
		// 따라서 true로 하면 컨트롤러 uri로 가게 됨		
		
		return true;
	}
	
	@Override
	public void postHandle (
			HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		System.out.println("postHandle 실행");
	}
	

}
