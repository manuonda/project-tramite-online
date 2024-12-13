import { Section } from '@models/section.interface';
import { Component , signal } from '@angular/core';
import { SectionCardComponent } from '@features/sections/components/section-card/section.card.component';
import { Question } from '@models/question.interface';

@Component({
  selector: 'app-sections',
  imports: [SectionCardComponent],
  templateUrl: './sections.component.html',
  styleUrl: './sections.component.scss'
})
export class SectionsComponent {

  initializeSection(): Section {
    return{
      id: null,
      description: 'Description',
      title: 'Title',
      enabled:false,
      sectionType: 'WIZARD'
    }
  }

   sections = signal<Section[]>([]);
   section = signal<Section>(this.initializeSection());

   addSection = () => {
     this.sections.update((sections) => [...sections, this.initializeSection()]);
   }

   addQuestion = (question: Question) => {
      console.log("add question");
   }

   removeSection = ( idSection: number | null ) => {

   }

   editQuestion(question:Question){

   }

   deleteQuestion(question: Question){

   }

   editSection(section:Section){
      console.log("handleSaveEdit aqui se ejecuta : ",section);
      this.sections()
   }

   toggleSection(section: Section){

   }
}
