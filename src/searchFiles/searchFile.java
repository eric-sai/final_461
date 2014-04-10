package searchFiles;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to search all file that contain the specific string
 *
 */
public class searchFile extends  Action{
    /**
     * run this method to search file table with specific name of file in the database
     * @param mapping  action mapping
     * @param form    searchform
     * @param request          HTTP request
     * @param response                   HTTP response
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Configuration configuration=new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        searchForm sf = (searchForm)form;
        String file = sf.getFilename();
        Query q = session.createQuery("select distinct new list(f.filename, f.createdate,f.size, d.directoryTree) from FileEntity f, FileDirEntity d where f.fid = d.fid ");
        List<List> list_f = q.list();
        List<List> ar =new ArrayList<List>();
        for(List i : list_f)
        {
            String filename = (String)i.get(0);
            if(filename.contains(file))
                ar.add(i);
        }

        session.getTransaction().commit();
        session.close();
        request.setAttribute("fullList", ar);
        return mapping.findForward("success");
    }

}
