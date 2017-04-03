package XML_Parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 30.03.2017.
 */
public class ParsingXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("C:\\Users\\traitorwtf\\IdeaProjects\\traitor\\src\\XML_Parsing\\Test.xml");

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(file);

        Element employer = (Element) document.getElementsByTagName("employers").item(0);
        String department = employer.getAttribute("department");

        NodeList employeeList = document.getElementsByTagName("employee");
        ArrayList<Employee> employees = new ArrayList<>();

        for (int i = 0; i < employeeList.getLength(); i++) {
            if (employeeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element emplElement = (Element) employeeList.item(i);
                Employee employee = new Employee();
                employee.setDepartment(department);
                employee.setNumber(Integer.valueOf(emplElement.getAttribute("number")));

                NodeList childNode = emplElement.getChildNodes();
                for (int j = 0; j < childNode.getLength(); j++) {
                    if (childNode.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNode.item(j);
                        switch (childElement.getNodeName()){
                            case "name": {
                                employee.setName(childElement.getTextContent());
                            }   break;
                            case "age": {
                                employee.setAge(Integer.valueOf(childElement.getTextContent()));
                            }   break;
                            case "salary": {
                                employee.setCurrency(childElement.getAttribute("currency"));
                                employee.setSalary(Integer.valueOf(childElement.getTextContent()));
                            }   break;
                        }

                    }
                }

                employees.add(employee);
            }
        }
        employees.forEach(System.out::println);


    }
}

class Employee{
    private String department;
    private int number;
    private String name;
    private int age;
    private String currency;
    private int salary;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" +
                "department='" + department + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary='" + currency + '\'' +
                " " + salary +
                '}';
    }
}
