import { NgClass } from '@angular/common';
import { Component, input, output ,model, ModelSignal, signal, NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faPencil, faCheck, faCoffee ,faChevronUp, faChevronDown} from '@fortawesome/free-solid-svg-icons';
import { Section } from '@models/section.interface';
import { title } from 'node:process';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'section-card',
  imports: [NgClass, FontAwesomeModule , FormsModule],
  templateUrl: './section.card.component.html',
  styleUrl: './section.card.component.scss'
})
export class SectionCardComponent {
    section = input.required<Section>();
    editSection = output<Section>();
    toggleSection = output<Section>();

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
        debugger;

        console.log("t");
        //this.section().description = this.editedDescription;
        //this.section().title = this.editedTitle;
        //this.section
        console.log(this.section());
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
}
