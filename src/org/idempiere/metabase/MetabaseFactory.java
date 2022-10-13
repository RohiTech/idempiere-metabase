package org.idempiere.metabase;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;

public class MetabaseFactory implements IFormFactory
{
	@Override
	public ADForm newFormInstance(String formName)
	{
		// TODO Auto-generated method stub
		if(formName.startsWith("org.idempiere.metabase"))
		{
			Object form=null;
			Class<?> clasz=null;
			ClassLoader loader = getClass().getClassLoader();
			
			try
			{
				clasz = loader.loadClass(formName);
				
				if(clasz != null)
					form = clasz.newInstance();
				
				if(form != null)
					return (ADForm) form;
				else if(form instanceof IFormController)
				{
					IFormController controller = (IFormController) form;
					ADForm adform = controller.getForm();
					adform.setICustomForm(controller);
					
					return adform;
				}
			}
			catch(Exception e)
			{
				System.out.println("" + e.getMessage());
			}
		}
		
		return null;
	}
}
