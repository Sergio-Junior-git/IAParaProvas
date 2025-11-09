import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GabaritoService {

  private apiUrl = 'http://localhost:8080/api/gabarito';

  constructor(private http : HttpClient) { }

  uploadTemplate(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/template`, data);
  }

  avaliarProva(file: File, templateId: number): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.apiUrl}/avaliar/${templateId}`, formData);
  }
}