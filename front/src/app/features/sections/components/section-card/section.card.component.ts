import { NgClass } from '@angular/common';
import { Component, input, output ,model, ModelSignal, signal, NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPencil, faCheck, faCoffee ,faChevronUp, faChevronDown} from '@fortawesome/free-solid-svg-icons';
import { Section } from '@models/section.interface';
import { title } from 'node:process';
import { FormsModule } from '@angular/forms';

import { QuestionCardComponent } from '@features/sections/components/question-card/question-card.component';
import { QuestionModalComponent } from '@features/questions/question-modal/question-modal.component';


@Component({
  selector: 'section-card',
  imports: [NgClass, FontAwesomeModule , FormsModule, QuestionCardComponent, QuestionModalComponent],
  templateUrl: './section.card.component.html',
  styleUrl: './section.card.component.scss'
})
export class SectionCardComponent {
    section = input.required<Section>();
    editSection = output<Section>();
    toggleSection = output<Section>();
    isQuestionModalOpen = signal<boolean>(false);


    /// icons
    icon = faCoffee;
    iconFaChevronUp = faChevronUp;
    iconFaChevronDown = faChevronDown;
    iconFaPencil = faPencil;
    iconFaCheck = faCheck;


    isExpanded = signal<boolean>(false);
    isEditing = signal<boolean>(false);
    editedTitle = '';
    editedDescription  = '';

    ngOnInit() {
      this.editedTitle = this.section().title;
      this.editedDescription = this.section().description;

    }



    handleSaveEdit() {
      console.log("handleSaveEdit");
         this.isExpanded.set(false);
         this.isExpanded.update( value => !value);

      if (this.isEditing()) {
        this.editSection.emit(this.section());
      }
      this.isEditing.update( value => !value);
    }

    handleToggleEnabled(event: MouseEvent) {
      event.stopPropagation();
      this.section().enabled = !this.section().enabled;
      this.toggleSection.emit(this.section());

    }

    onEditSection(){
       if(this.isEditing()){
          this.editSection.emit(this.section());
       }
    }

    toggleExpanded(){
      console.log("toggleExpanded")
      if(!this.isEditing()){
        console.log("is not  Editing");
         this.isExpanded.update( value => !value);
      }
    }

    onEditQuestion(id:number){
      console.log("onEditQuestion : "+ id)
    }

    onDeleteQuestion(id: number){
      console.log("onDeleteQuestion: "+ id)
    }

    openQuestionModal(){
      this.isQuestionModalOpen.update(value => !value);
    }

    onCloseModal(value:boolean){
      console.log("value obutengiod: ", value);
      this.isQuestionModalOpen.update( value => !value);
    }


}
