import { NgClass } from '@angular/common';
import { Component, input, output ,model, ModelSignal, signal } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCoffee ,faChevronUp, faChevronDown} from '@fortawesome/free-solid-svg-icons';
import { Section } from '@models/section.interface';

@Component({
  selector: 'section-card',
  imports: [NgClass, FontAwesomeModule],
  templateUrl: './section.card.component.html',
  styleUrl: './section.card.component.scss'
})
export class SectionCardComponent {
    section = model.required<Section>();
    editSection = output<Section>();
    toggleSection = output<Section>();
    icon = faCoffee;
    IconFaChevronUp = faChevronUp;
    IconFaChevronDown = faChevronDown;


    isExpanded = signal<boolean>(false);
    isEditing = signal<boolean>(false);
    editedTitle = '';
    editedDescription  = '';

    ngOnInit() {
      this.editedTitle = this.section().title;
      this.editedDescription = this.section().description;

    }



    handleSaveEdit(event: MouseEvent) {
      event.stopPropagation();
      if (this.isEditing()) {
        this.section().title = this.editedTitle;
        this.section().description = this.editedDescription;
        this.editSection.emit(this.section());
      }
      //this.isEditing.update(!this.isEditing());
    }

    handleToggleEnabled(event: MouseEvent) {
      event.stopPropagation();
      this.section().enabled = !this.section().enabled;
      this.toggleSection.emit(this.section());

    }

    onEditSection(){
       //this.editSection.emit(this.section);
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
