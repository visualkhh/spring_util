package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class InputVariantBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "VARIANT_ID")
    @JsonView({JsonViewFrontEnd.class})
    Long variantId;

    @Column(name = "GENE_REFGENE")
    @JsonView({JsonViewFrontEnd.class})
    String geneRefgene;

    @Column(name = "DISEASE")
    @JsonView({JsonViewFrontEnd.class})
    String disease;

    @Column(name = "INHERITANCE")
    @JsonView({JsonViewFrontEnd.class})
    String inheritance;

    @Column(name = "CHR")
    @JsonView({JsonViewFrontEnd.class})
    String chr;

    @Column(name = "START")
    @JsonView({JsonViewFrontEnd.class})
    String start;

    @Column(name = "END")
    @JsonView({JsonViewFrontEnd.class})
    String end;

    @Column(name = "REF")
    @JsonView({JsonViewFrontEnd.class})
    String ref;

    @Column(name = "ALT")
    @JsonView({JsonViewFrontEnd.class})
    String alt;

    @Column(name = "FUNC_REFGENE")
    @JsonView({JsonViewFrontEnd.class})
    String funcRefgene;

    @Column(name = "GENEDETAIL_REFGENE")
    @JsonView({JsonViewFrontEnd.class})
    String genedetailRefgene;

    @Column(name = "EXONICFUNC_REFGENE")
    @JsonView({JsonViewFrontEnd.class})
    String exonicfuncRefgene;

    @Column(name = "AACHANGE_REFGENE")
    @JsonView({JsonViewFrontEnd.class})
    String aachangeRefgene;

    @Column(name = "EXON_TRANSCRIPTIDS")
    @JsonView({JsonViewFrontEnd.class})
    String exonTranscriptids;

    @Column(name = "EXON_POSITION")
    @JsonView({JsonViewFrontEnd.class})
    String exonPosition;

    @Column(name = "EXON_DISTANCE")
    @JsonView({JsonViewFrontEnd.class})
    String exonDistance;

    @Column(name = "ESP6500_ALL")
    @JsonView({JsonViewFrontEnd.class})
    String esp6500All;

    @Column(name = "EXAC_ALL")
    @JsonView({JsonViewFrontEnd.class})
    String exacAll;

    @Column(name = "EXAC_AFR")
    @JsonView({JsonViewFrontEnd.class})
    String exacAfr;

    @Column(name = "EXAC_AMR")
    @JsonView({JsonViewFrontEnd.class})
    String exacAmr;

    @Column(name = "EXAC_EAS")
    @JsonView({JsonViewFrontEnd.class})
    String exacEas;

    @Column(name = "EXAC_FIN")
    @JsonView({JsonViewFrontEnd.class})
    String exacFin;

    @Column(name = "EXAC_NFE")
    @JsonView({JsonViewFrontEnd.class})
    String exacNfe;

    @Column(name = "EXAC_OTH")
    @JsonView({JsonViewFrontEnd.class})
    String exacOth;

    @Column(name = "EXAC_SAS")
    @JsonView({JsonViewFrontEnd.class})
    String exacSas;

    @Column(name = "1000G2015AUG_ALL")
    @JsonView({JsonViewFrontEnd.class})
    String _1000g2015augAll;

    @Column(name = "SNP138")
    @JsonView({JsonViewFrontEnd.class})
    String snp138;

    @Column(name = "CLINSIG")
    @JsonView({JsonViewFrontEnd.class})
    String clinsig;

    @Column(name = "CLNDBN")
    @JsonView({JsonViewFrontEnd.class})
    String clndbn;

    @Column(name = "CLNACC")
    @JsonView({JsonViewFrontEnd.class})
    String clnacc;

    @Column(name = "CLNDSDB")
    @JsonView({JsonViewFrontEnd.class})
    String clndsdb;

    @Column(name = "CLNDSDBID")
    @JsonView({JsonViewFrontEnd.class})
    String clndsdbid;

    @Column(name = "SIFT_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String siftScore;

    @Column(name = "SIFT_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String siftPred;

    @Column(name = "POLYPHEN2_HDIV_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String polyphen2HdivScore;

    @Column(name = "POLYPHEN2_HDIV_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String polyphen2HdivPred;

    @Column(name = "POLYPHEN2_HVAR_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String polyphen2HvarScore;

    @Column(name = "POLYPHEN2_HVAR_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String polyphen2HvarPred;

    @Column(name = "LRT_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String lrtScore;

    @Column(name = "LRT_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String lrtPred;

    @Column(name = "MUTATIONTASTER_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String mutationtasterScore;

    @Column(name = "MUTATIONTASTER_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String mutationtasterPred;

    @Column(name = "MUTATIONASSESSOR_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String mutationassessorScore;

    @Column(name = "MUTATIONASSESSOR_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String mutationassessorPred;

    @Column(name = "FATHMM_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String fathmmScore;

    @Column(name = "FATHMM_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String fathmmPred;

    @Column(name = "RADIALSVM_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String radialsvmScore;

    @Column(name = "RADIALSVM_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String radialsvmPred;

    @Column(name = "LR_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String lrScore;

    @Column(name = "LR_PRED")
    @JsonView({JsonViewFrontEnd.class})
    String lrPred;

    @Column(name = "VEST3_SCORE")
    @JsonView({JsonViewFrontEnd.class})
    String vest3Score;

    @Column(name = "CADD_RAW")
    @JsonView({JsonViewFrontEnd.class})
    String caddRaw;

    @Column(name = "CADD_PHRED")
    @JsonView({JsonViewFrontEnd.class})
    String caddPhred;

    @Column(name = "GERP_RS")
    @JsonView({JsonViewFrontEnd.class})
    String gerpRs;

    @Column(name = "PHYLOP46WAY_PLACENTAL")
    @JsonView({JsonViewFrontEnd.class})
    String phylop46wayPlacental;

    @Column(name = "PHYLOP100WAY_VERTEBRATE")
    @JsonView({JsonViewFrontEnd.class})
    String phylop100wayVertebrate;

    @Column(name = "SIPHY_29WAY_LOGODDS")
    @JsonView({JsonViewFrontEnd.class})
    String siphy29wayLogodds;

    @Column(name = "OTHERINFO")
    @JsonView({JsonViewFrontEnd.class})
    String otherinfo;

    @Column(name = "OMIM_GENE")
    @JsonView({JsonViewFrontEnd.class})
    String omimGene;

//    @Column(name = "alternative TITLES")
//    @JsonView({JsonViewFrontEnd.class})
//    String titles;

    @Column(name = "LOCATION")
    @JsonView({JsonViewFrontEnd.class})
    String location;

    @Column(name = "PHENOTYPE")
    @JsonView({JsonViewFrontEnd.class})
    String phenotype;

    @Column(name = "PHENOTYPEMIMNUMBER")
    @JsonView({JsonViewFrontEnd.class})
    String phenotypeMIMnumber;

    @Column(name = "PHENOTYPEMAPPINGKEY")
    @JsonView({JsonViewFrontEnd.class})
    String phenotypemappingkey;

    @Column(name = "GENE_LOCUS")
    @JsonView({JsonViewFrontEnd.class})
    String geneLocus;

    @Column(name = "GENE_LOCUSMIMNUMBER")
    @JsonView({JsonViewFrontEnd.class})
    String geneLocusmimnumber;

}
