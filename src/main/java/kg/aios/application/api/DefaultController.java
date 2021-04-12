package kg.aios.application.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kg.aios.application.model.Company;
import kg.aios.application.model.CompanyField;
import kg.aios.application.model.JobApplication;
import kg.aios.application.model.JobApplicationField;
import kg.aios.application.model.Position;
import kg.aios.application.service.ApplicationService;
import kg.aios.application.service.PositionService;

@RestController
@RequestMapping({ "/api" })
public class DefaultController {

	private String[] firstNames = { "Bernice", "Dinorah", "Karren", "James", "Kristan", "Fonda", "Aldo", "Nelle",
			"Cecille", "Shaun", "Kattie", "Darlene", "Chastity", "Arlena", "Hans", "King", "Jesica", "Lynwood",
			"Trisha", "Charlena", "Macy", "Columbus", "Catherina", "Aileen", "Raisa", "Lulu", "Bruce", "Tad", "Natacha",
			"Efren", "Eleanora", "Scot", "Nilsa", "Kenna", "Meridith", "Francisco", "So", "Minh", "Pierre", "Delois",
			"Jettie", "Katelyn", "Herta", "Branden", "Phebe", "Jeanetta", "Lucretia", "Vincent", "Latonia", "Olive",
			"Jerica", "Maurita", "Rolande", "Gemma", "Hyacinth", "Josue", "Markus", "Hayley", "Takisha", "Kerstin",
			"Vincenzo", "Marcell", "Maire", "Dominica", "Olevia", "Roma", "Kathie", "Diane", "Lowell", "Catarina",
			"Orville", "Shantae", "Micha", "Liza", "Marcene", "Michaela", "Rosina", "Elizabet", "Remona", "Jennifer",
			"Erminia", "Reita", "Alejandrina", "Lajuana", "Gertie", "Tashina", "Cindy", "Hester", "Junita",
			"Jacquelynn", "Rubye", "Eda", "Kit", "Iraida", "Henry", "Temika", "Woodrow", "Andrew", "Kimbra",
			"Margaretta" };

	private String[] lastNames = { "Asmus", "Steffenson", "Cully", "Drost", "Rench", "Bontrager", "Fleming", "Mcgreevy",
			"Coplin", "Locher", "Maxie", "Sorrell", "Wassink", "Gracie", "Reiling", "Kerner", "Gaskill", "Cornelison",
			"Valade", "Pasillas", "Lavallee", "Strange", "Dillion", "Loera", "Certain", "Schechter", "Hartlage",
			"Voltz", "Cullen", "Smithers", "Losoya", "Johnston", "Rishel", "Fogel", "Earnest", "Mershon", "Walston",
			"Lieberman", "Madonia", "Tipler", "Shenk", "Buswell", "Snowden", "Griswold", "Fabiano", "Funes", "Smeltzer",
			"Helbig", "Parenti", "Stmarie", "Caggiano", "Amell", "Simmon", "Pratico", "Stever", "Uyehara", "Wiste",
			"Hubert", "Watchman", "Alder", "Cowley", "Frew", "Cutshall", "Laughlin", "Bonnet", "Ziolkowski", "Buchanan",
			"Rogers", "Backstrom", "Kauzlarich", "Hatmaker", "Seawright", "Babineau", "Suder", "Dahlen", "Coutu", "Mix",
			"Mengel", "Christon", "Goldstein", "Sessler", "Felipe", "Piggott", "Warwick", "Cleary", "Fetterolf",
			"Mclawhorn", "Mcmillian", "Phipps", "Lutterman", "Poehler", "Mainer", "Hoobler", "Kinghorn", "Beehler",
			"Valliere", "Stivers", "Buice", "Mcglade", "Goguen" };

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value = { "/", "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String initialPage() {

		return "It works!";
	}

	@GetMapping("/positions")
	public List<Position> getPositions() {
		return positionService.findAllPositions();
	}

	@GetMapping("/populate")
	public String pupulate() {
		Random rand = new Random();
		for (int i = 0; i < 20000; i++) {
			JobApplication application = new JobApplication();
			application.setCompany(new Company());
			application.getCompany().setId((long) (rand.nextInt(2) + 1));
			application.setPosition(new Position());
			application.getPosition().setId((long) (rand.nextInt(4) + 1));

			application.setFirstName(firstNames[rand.nextInt(firstNames.length)]);
			application.setLastName(lastNames[rand.nextInt(lastNames.length)]);
			application.setEmail(randomString() + "@gmail.com");
			application.setCvUrl(randomString() + "/" + randomString());

			application.setFields(new ArrayList<>());
			if (application.getCompany().getId() == 1) {
				JobApplicationField field = new JobApplicationField();
				field.setField(new CompanyField());
				field.getField().setId(1l); // Phone
				field.setValue(rand.nextInt(1000000000) + "");
				application.getFields().add(field);
				
				field = new JobApplicationField();
				field.setField(new CompanyField());
				field.getField().setId(2l); // Experience
				field.setValue(rand.nextInt(40) + "");
				application.getFields().add(field);
			} else {
				JobApplicationField field = new JobApplicationField();
				field.setField(new CompanyField());
				field.getField().setId(4l); // Expectation
				field.setValue(rand.nextInt(1000000) + "");
				application.getFields().add(field);

				field = new JobApplicationField();
				field.setField(new CompanyField());
				field.getField().setId(5l); // Linkedin url
				field.setValue("https://linkedin.com/" + randomString());
				application.getFields().add(field);

				field = new JobApplicationField();
				field.setField(new CompanyField());
				field.getField().setId(6l); // Cover letter url
				field.setValue("https://" + randomString() + ".com/" + randomString());
				application.getFields().add(field);
			}
			
			applicationService.addApplication(application);
		}

		return "completed";
	}

	private String randomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

}
