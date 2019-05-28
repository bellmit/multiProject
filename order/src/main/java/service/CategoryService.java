package service;

import dao.interfaces.CategoryDao;
import domain.Category;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryService {
    @Inject
    private CategoryDao cd;

    public Category create(Category c){
        return cd.create(c);
    }

    public Category find(String id){
        return cd.find(id);
    }

    public Category edit(Category c){
        return cd.edit(c);
    }

    public void delete(Category c){
        cd.delete(c);
    }

    public List<Category> getAll(){
        return cd.getAll();
    }
}
