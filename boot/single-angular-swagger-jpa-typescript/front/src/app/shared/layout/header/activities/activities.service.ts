import { Injectable } from '@angular/core';
import {JsonApiService} from "../../../../core/services/json-api.service";

@Injectable()
export class ActivitiesService {

  url: string;

  constructor(private jsonApiService: JsonApiService) {
    this.url = '/activities/activities.json';
  }

  getActivities()  {
    return this.jsonApiService.fetch(this.url)
  }

}
