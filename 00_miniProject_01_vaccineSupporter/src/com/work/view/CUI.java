package com.work.view;

import java.io.IOException;

import com.work.exception.DuplicateException;

/**
 * <pre>
 * 백신도우미 시스템 CUI 시작 클래스
 * </pre>
 *  
 * @author 김기영
 * @version ver.1.0
 * @since jdk1.8
 */
public class CUI {

	/**
	 * 백신도우미 시스템 CUI 시작 메서드
	 * @param args 메인
	 * @throws DuplicateException  중복예외
	 */
	public static void main(String[] args) throws DuplicateException {
		/* 관리 메뉴 */
		Menu view = new Menu();

		/* 초기화 메뉴 수행 */
		view.initMenu();

		while(true) {
			try {
				view.mainMenu();
			} catch (IOException e) {
				System.out.println("[오류] 입력형식 오류");;
			}
		}
	}
}
