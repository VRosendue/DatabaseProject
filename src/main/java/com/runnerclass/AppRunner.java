package runnerclass;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import services.CrudService;

@Component
public class AppRunner implements ApplicationRunner {

	private final CrudService crudservice;

	public AppRunner(CrudService crudservice) {
		this.crudservice = crudservice;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		crudservice.createCustomer();
		crudservice.findCustomerById();
		crudservice.updateCustomer();
		crudservice.delete();

	}

}
