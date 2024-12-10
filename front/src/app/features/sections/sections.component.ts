import { Section } from './../../models/section.interface';
import { Component , signal } from '@angular/core';
import { CardComponent } from './components/card/card.component';

@Component({
  selector: 'app-sections',
  imports: [CardComponent],
  templateUrl: './sections.component.html',
  styleUrl: './sections.component.scss'
})
export class SectionsComponent {

  initializeSection(): Section {
    return{
      id: null,
      description: '',
      name: '',
      enabled:false,
      sectionType: 'WIZARD'
    }
  }

   sections = signal<Section[]>([]);
   section = signal<Section>(this.initializeSection());

   addSection = () => {
     this.sections.update((sections) => [...sections, this.initializeSection()]);
   }
}
