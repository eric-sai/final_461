package historyMachineController;

import UserManagement.session.AuthorityUtil;
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
import java.util.List;

/**
 * this class is used to search all file in deleted file table
 */
public class historyBack extends Action {
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception{

         Session session = null;
        Configuration configuration=new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session =sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select  distinct new list(f.filename, f.createdate, f.size, f.fid) from DeletedFileEntity f where f.user.uId=?").setString(0, String.valueOf(AuthorityUtil.getUser(request).getUserId()));
        List<List> list_f = q.list();
        System.out.print("Abcd");
        session.getTransaction().commit();
        session.close();
        request.setAttribute("list",list_f);
        return mapping.findForward("history");

    }


}

