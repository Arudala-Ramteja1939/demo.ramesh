package UnWantedClasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {

	//http://localhost:8080/get
	
	@GetMapping("/get")
	public List<My> get()
	{
		
		List<My> l=new ArrayList<>();
		l.add(new My(1l,"ram",22l));
		l.add(new My(2l,"ram",22l));
		l.add(new My(3l,"ram",22l));
		l.add(new My(4l,"ram",22l));
		l.add(new My(5l,"ram",22l));
		
		return l;
		
	}
	
	@GetMapping("/gets")
	public String helloWorld()
	{
		return "Hello World";
	}
	
}
