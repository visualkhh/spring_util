import {AuthDetail, AuthDepthDetail, CrudTypeCd, UseCd, UserDetails} from '@web-core-generate/models';

const userDetail = {
    admNm: 'Guest',
    useCd: 'USE002'
} as  UserDetails;

const home = {
    url: '/home',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE002',
} as AuthDepthDetail;


const admin = {
    url: '/admin',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE002',
} as AuthDepthDetail;
const adminSub = {
    url: '/admin/account',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE001',
} as AuthDepthDetail;
admin.childs = [].concat(adminSub);



const patients = {
    url: '/patients',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE002',
} as AuthDepthDetail;
const patientsSub = {
    url: '/patients/[0-9]+',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE001',
} as AuthDepthDetail;
const patientsSub2 = {
    url: '/patients/[0-9]+/hello',
    crudTypeCd: 'GET',
    useCd: 'USE001',
    regexpCd: 'USE001',
} as AuthDepthDetail;
patientsSub.childs = [].concat(patientsSub2);
patients.childs = [].concat(patientsSub);

userDetail.authDupDepthDetails = [].concat([home, admin, patients]);
// userDetail.authDupDepthDetails = [].concat([home, admin, patients]);
// userDetail.authDupDepthDetails = [].concat([patients]);


export const userDetails: UserDetails = userDetail;
