import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from '../model/User';
import {UserCalculateRequest} from '../model/UserCalculateRequest';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {
  private BRACKET_PATH = environment.SCORE_SEGMENT_PATH + "/monthlyIncomeBracket";
  private USER_PATH = environment.SCORE_SEGMENT_PATH + "/user";
  private CITY_PATH = environment.CITY_SCORE_PATH + "/city";

  constructor(private httpClient: HttpClient) {

  }

  httpOptions = {
    headers: new HttpHeaders(
      {'Content-Type': 'application/json'}
    )
  };

  getAllMonthlyIncomeBracket(): Observable<any> {
    return this.httpClient.get(this.BRACKET_PATH);
  }
  getAllCities(): Observable<any> {
    return this.httpClient.get(this.CITY_PATH);
  }
  calculateUserScore(request: UserCalculateRequest): Observable<any>{
   return this.httpClient.post(this.USER_PATH, JSON.stringify(request), this.httpOptions);
  }

}
