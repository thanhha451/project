package com.spring.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * GlobalExceptionHandling class để xử lý các ngoại lệ toàn cục trong ứng dụng.
 * Sử dụng @ControllerAdvice để áp dụng cho toàn bộ các controller.
 * 
 * @author le thanh ha (04/05/2001)
 */
/* @ControllerAdvice */
public class GlobalExceptionHandling {

    /**
     * Xử lý các ngoại lệ chung chung.
     *
     * @param model đối tượng Model để truyền dữ liệu đến view
     * @param ex    đối tượng Exception được ném ra
     * @return tên của view để hiển thị trang lỗi
     */
    @ExceptionHandler(Exception.class)
    private String processGenericException(Model model, Exception ex) {
        model.addAttribute("exception", ex);
        return "layout/error";
    }

    /**
     * Xử lý ngoại lệ khi không tìm thấy handler cho yêu cầu.
     *
     * @param model đối tượng Model để truyền dữ liệu đến view
     * @param ex    đối tượng NoHandlerFoundException được ném ra
     * @return tên của view để hiển thị trang lỗi
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    private String processNotFoundException(Model model, Exception ex) {
        model.addAttribute("exception", ex);
        return "layout/error";
    }
}
