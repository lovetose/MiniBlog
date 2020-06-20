import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogService {
	
	@Autowired
	private BlogJDBC blogJdbc;

	public BlogModel getActiveBlog() {
		return blogJdbc.getActiveBlog();
	}
	
	 public int editBlog(BlogModel blog) {
		 return blogJdbc.editBlog(blog);
	 }
	 
	 public int DeleteBlog(String blogId, String author) {
		 return blogJdbc.deleteBlog(blogId, author);
	 }
	
	
}
