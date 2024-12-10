import { Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class StorageService {

  private readonly STORAGE_KEY='tramite_storage';

  loadState() {
    try {
       const stored =localStorage.getItem(this.STORAGE_KEY);
       return stored ? JSON.parse(stored) : null;
    } catch (error) {
      console.error(`Error load Storage Tramite: ${error}`);
      return null;
    }
  }

  // saveState(state: any): null {

  // }


}
