package UserManagement.action;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import javax.servlet.http.HttpServletRequest;

/**
 *   this class is a basic class for other action to extend and show page waning informat
 */

public class BaseAction extends Action {


    /**
     * this method is to show warning message
     * @param request
     * @param key
     * @param values
     */

    public void addMessage(HttpServletRequest request, String key,
			String[] values)
	{

		ActionMessages messages = (ActionMessages) request
				.getAttribute(Globals.ERROR_KEY);
		if (messages == null)
			messages = new ActionMessages();


		if (values != null && values.length > 0)
			messages.add(Globals.ERROR_KEY, new ActionMessage(key, values));
		else
			messages.add(Globals.ERROR_KEY, new ActionMessage(key));
		

		request.setAttribute(Globals.ERROR_KEY, messages);
	}

    /**
     * this method is to show warning information
     * @param request
     * @param key
     */
	public void addMessage(HttpServletRequest request, String key)
	{
		addMessage(request, key, null);
	}
	
}
