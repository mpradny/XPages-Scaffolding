package frostillicus.xsp.model.component;

import javax.faces.context.FacesContext;

import frostillicus.xsp.controller.ComponentMapAdapter;
import frostillicus.xsp.controller.ComponentMapAdapterFactory;
import frostillicus.xsp.model.ModelObject;

public class ModelComponentMapAdapterFactory implements ComponentMapAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public ComponentMapAdapter createAdapter(final Object obj) {
		if(obj instanceof ModelObject) {
			return new ModelComponentMapAdapter((ModelObject)obj);
		} else if(obj instanceof String) {
			// It may be a class name
			try {
				Class<?> clazz = Class.forName(String.valueOf(obj),true,FacesContext.getCurrentInstance().getContextClassLoader());
				if(ModelObject.class.isAssignableFrom(clazz)) {
					return new ModelClassComponentMapAdapter((Class<? extends ModelObject>)clazz);
				}
			} catch(Exception e) {
				// Ignore
			}
		}
		return null;
	}
}
