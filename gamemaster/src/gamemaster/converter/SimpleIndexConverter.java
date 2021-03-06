package gamemaster.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import gamemaster.model.EntityGenerica;

@FacesConverter(value="simpleIndexConverter")
public class SimpleIndexConverter implements Converter {


    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
        if (value != null) {  
            return this.getAttributesFrom(component).get(value);  
        }  
        return null;  
    }  

    public String getAsString(FacesContext ctx, UIComponent component, Object value) { 
        if (value != null  
                && !"".equals(value)) {  

            EntityGenerica entity = (EntityGenerica) value;  

            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);  

            Long codigo = entity.getId();  
            if (codigo != 0) {  
                return String.valueOf(codigo);  
            }  
        }  

        return (String) value;  
    }  

    protected void addAttribute(UIComponent component, EntityGenerica o) {  
        String key = Long.toString(o.getId()); // codigo como chave neste caso  
        this.getAttributesFrom(component).put(key, o);  
    }  

    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  


}
