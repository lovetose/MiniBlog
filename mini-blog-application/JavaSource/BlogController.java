import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HttpServletBean;


public class BlogController {
	@Autowired
	private BlogService blogService;

	@RequestMapping("/main")
	public BlogModel main(HttpServletBean request, HttpServletBean response, @RequestBody BlogModel blogModel) {
		BlogModel returnObject = null;
		if(blogModel.getAction().equalsIgnoreCase("MainPage")) {
			returnObject = blogService.getActiveBlog();
		}
		else if(blogModel.getAction().equalsIgnoreCase("delete")) {
			blogService.DeleteBlog(blogModel.getBlogId(), blogModel.getAuthor());
		}
		else if(blogModel.getAction().equalsIgnoreCase("edit")) {
			blogService.editBlog(blogModel);		
		}			
		return returnObject;
	}// Main point of returning object for only MainPage is because to reduce loading time,
	// We need to implement Action = Mainpage everytime after editing or deleting action is Done;
}
