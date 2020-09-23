package com.genome.dx.core.model.phenoType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.genome.dx.core.domain.base.PhenoTypeBase;
import com.genome.dx.core.model.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhenoTypeTreeForBootstrapTreeView extends ModelBase {
    public Long id;
    public String text;
    @JsonIgnore
    public Long parentId;
    public List<PhenoTypeTreeForBootstrapTreeView> nodes;


    public PhenoTypeTreeForBootstrapTreeView(Long id, String text, Long parentId) {
        this.id = id;
        this.text = text;
        this.parentId = parentId;
    }

    public boolean deepAddChild(PhenoTypeTreeForBootstrapTreeView child) {
        if (this.id.equals(child.parentId)) {
            if (this.hasChildrenTermId(child.id)){
                return false;
            }else{
                this.addChild(child);
                return true;
            }
        } else {
            if (null != this.nodes) {
                for (int i = 0; i < this.nodes.size() ; i++) {
                    PhenoTypeTreeForBootstrapTreeView it = this.nodes.get(i);
                    if (it.deepAddChild(child)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addChild(PhenoTypeTreeForBootstrapTreeView items) {
        if (null == this.nodes) {
            this.nodes = new ArrayList<>();
        }
        this.nodes.add(items);
    }
    public boolean hasChildrenTermId(Long termid) {
        if (null != this.nodes) {
            for (PhenoTypeTreeForBootstrapTreeView at : this.nodes) {
                if (at.id.equals(termid)) {
                    return true;
                }
            }
        } else{
            return false;
        }
        return false;
    }

}
