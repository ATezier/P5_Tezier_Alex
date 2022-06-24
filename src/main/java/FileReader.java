import java.util.List;

public class FileReader {
    private String filepath;

    public FileReader(String path){
        if(path != null) this.filepath = path;
        else throw new IllegalArgumentException("The path is null.");
    }

    public void read(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords){

    }
}
