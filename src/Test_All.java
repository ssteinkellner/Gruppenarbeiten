import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Test_GummiEnte.class,
	Test_StockEnte.class,
	Test_MoorEnte.class,
	Test_Gans.class,
	Test_GansAdapter.class,
	
	Test_LockPfeife.class,
	Test_Beobachter.class,
	
	Test_Schar.class,
	Test_QuakZaehler.class,
	
	Test_EntenFabrik.class,
	Test_ZaehlendeEntenFabrik.class
})
public class Test_All { }
