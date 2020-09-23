package com.genome.dx.core.model.phenoType;

import com.genome.dx.core.domain.base.PhenoTypeBase;

import java.util.ArrayList;
import java.util.List;

public class PhenoTypeTree extends PhenoTypeBase {
    public List<PhenoTypeTree> children;

    public boolean deepAddChild(PhenoTypeTree child) {
        if (this.termid.equals(child.parentTermid)) {
            if (this.hasChildrenTermId(child.termid)){
                return false;
            }else{
                this.addChild(child);
                return true;
            }
        } else {
            if (null != this.children) {
                for (int i = 0; i < this.children.size() ; i++) {
                    PhenoTypeTree it = this.children.get(i);
                    if (it.deepAddChild(child)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addChild(PhenoTypeTree items) {
        if (null == this.children) {
            this.children = new ArrayList<>();
        }
        this.children.add(items);
        // this.childrenIndex[items.termid] = items;
    }
    public boolean hasChildrenTermId(Long termid) {
        if (null != this.children) {
            for (PhenoTypeTree at : this.children) {
                if (at.termid.equals(termid)) {
                    return true;
                }
            }
        } else{
            return false;
        }
        return false;
    }

    public String toString(){
        return "[termid:" + this.termid + ", parent_termid:" + this.parentTermid + ", lvl:" + this.lvl+"]";
    }
}
