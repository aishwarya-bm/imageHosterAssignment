package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.repository.CommentRepository;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
//@ComponentScan("ImageHoster.service")
//@ComponentScan("ImageHoster.repository")
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Comment comment, HttpSession session)
    {
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");
        comment.setDate(LocalDate.now());
        comment.setImage(image);
        comment.setUser(user);
        commentService.createComment(comment);
        //return "images/image";
//        List<Comment> comments = image.getComments();
//        model.addAttribute("image", image);
//        model.addAttribute("tags", image.getTags());
//        model.addAttribute("comments",comments);
        return "redirect:/images/{imageId}/{imageTitle}";
    }
}
