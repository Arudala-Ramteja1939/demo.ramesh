package UnWantedClasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Base level url  @RequestMapping
@RequestMapping("/students")
public class StudentController {

	@GetMapping("/getStudent")
	public ResponseEntity<Student> getStudent()
	{
		Student s=new Student(1,"Arudala","Ram Teja");
		//return new ResponseEntity<>(s,HttpStatus.OK);
		return ResponseEntity.ok().header("ram","teja").body(s);

	}

	@GetMapping("/getStudentList")
	public ResponseEntity<List<Student>> list()
	{
		List<Student> l=new ArrayList<>();
		l.add(new Student(1,"Arudala","Ram Teja"));
		l.add(new Student(2,"Arudala","Lakshman"));
		l.add(new Student(3,"Arudala","Sambaiah"));
		return new ResponseEntity<>(l,HttpStatus.CREATED);
		//return ResponseEntity.ok(l);

	}

	@GetMapping("/get/{id}")
	public Student getById(@PathVariable("id") int id)
	{
		//List<Student> m=this.list();
		//for(Student w:m)
		{
		//	if(w.getId()==id)
			{
		//		return w;
			}

		}
		// m.stream().filter(w -> w.getId()==id).collect(Collectors.toList());
		return null;

	}

	//http://localhost:8080/getParam?id=111&firstName=Ramteja&lastName=Arudala
	@GetMapping("/getParam")
	public Student requestPqram(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName)
	{

		return new Student(id,firstName,lastName);

	}

	//Spring boot Rest API that handles HTTP POST Requset
	//@PostMapping and @RequestBody
	@PostMapping("/students/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Student create(@RequestBody Student student)
	{
		return student;

	}

	//Spring boot Rest API that handles HTTP PUT Request
	//@PutMapping and @RequestBody
	@PutMapping("/students/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Student update(@RequestBody Student student,@PathVariable int id)
	{
		Student s=this.getById(id);
		if(s!=null)
		{
			s.setFirstName(student.getFirstName());
			s.setLastName(student.getLastName());
		}
		return s;

	}

	//Spring boot Rest API that handles HTTP DELETE Request
	//@DeleteMapping
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  delete(@PathVariable int id)
	{
	
		return ResponseEntity.ok("Student successfully deleted");
		
	}
}
