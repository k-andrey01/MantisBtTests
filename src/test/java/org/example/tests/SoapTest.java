package org.example.tests;

import org.example.model.Issue;
import org.example.model.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTest extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.getSoapHelper().getProjects();

        System.out.println(projects.size());
        projects.stream().map((m) -> m.getName()).forEach(System.out::println);
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.getSoapHelper().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("test issue desc")
                .withProject(projects.iterator().next());
        Issue created = app.getSoapHelper().addIssue(issue);

        assertEquals(issue.getSummary(), created.getSummary());
    }
}
