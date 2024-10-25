package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController điều khiển các yêu cầu đến trang chủ của ứng dụng.
 * 
 * @version 1.0
 * @date 06-06-2024
 * 
 * <p>Modification Logs:</p>
 * <pre>
 * DATE                 AUTHOR            DESCRIPTION
 * --------------------------------------------------------------------
 * 06-06-2024           Le Thanh Ha       Create
 * </pre>
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Xử lý yêu cầu GET cho trang chủ.
     *
     * @param model đối tượng Model để truyền dữ liệu đến view
     * @return tên của view để hiển thị trang chủ
     */
    @GetMapping("")
    public String home(Model model) {
        // Trả về view "layout/default" để hiển thị trang chủ
        return "layout/default";
    }
}
