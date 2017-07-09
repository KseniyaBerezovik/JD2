package by.itacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
//
//    @Autowired
//    ServletContext servletContext;
//
//    @GetMapping("/addimg")
//    public String index() {
//        return "addimg";
//    }
//
//    @PostMapping("/addimg")
//    public String singleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
//        String webappRoot = servletContext.getRealPath("/");
//        String relativeFolder = File.separator + "resources" + File.separator
//                + "images" + File.separator;
//        String filename = webappRoot + relativeFolder
//                + file.getOriginalFilename();
//
//        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:uploadStatus";
//        }
//
//        try {
//
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(filename);
//            System.out.println(filename);
//            Files.write(path, bytes);
//
//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/main_page";
//    }
}
