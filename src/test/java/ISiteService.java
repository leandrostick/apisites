import java.io.FileNotFoundException;
import java.util.Collection;

public interface ISiteService {

    public Collection<Site> getSites() throws FileNotFoundException;
    public Collection<Category> getCategories(String id);

}
