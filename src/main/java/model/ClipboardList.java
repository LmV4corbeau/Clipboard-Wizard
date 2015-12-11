package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanne on 08.12.2015.
 */
public class ClipboardList implements Serializable {
    private List list;

    public ClipboardList() {
        this.list = new ArrayList();
    }

    public List getList() {
        return this.list;
    }
}
