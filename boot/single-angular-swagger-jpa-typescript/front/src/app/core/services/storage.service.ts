import { Injectable } from '@angular/core';
import { Storage } from '@ionic/storage';
import { environment } from '@env/environment';


@Injectable()
export class StorageService {

  constructor(
    private storage: Storage
  ) { }


  get(key: any){
    return this.load().then(db => db[key])
  }

  /**
   * @returns value
   * @param key
   * @param value
   */
  set(key: any, value: any){
    return this.load().then(db => {
       db[key] = value
       return db
    }).then(this.dump).then(_ => value)
  }

  remove(key: any){
    return this.load().then(db => {
       db[key] = null
       delete db[key]
       return db
    }).then(this.dump)
  }

  dump = (db: any) => {
    return this.storage.set(environment.smartadmin.db, JSON.stringify(db))
  }

  load(){
    return this.storage.get(environment.smartadmin.db).then( (db:any) => {
      return db ? JSON.parse(db) : {}
    })
  }

}
