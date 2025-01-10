import { Component, input, output } from '@angular/core';
import { Question } from '@models/question.interface';
import { faPencil, faCheck, faCoffee ,faChevronUp, faChevronDown} from '@fortawesome/free-solid-svg-icons';

export interface QuestionModalProps{
  isOpen: boolean;
  onClose: () => void;

}


@Component({
  selector: 'question',
  imports: [],
  templateUrl: './question-card.component.html',
  styleUrl: './question-card.component.scss'
})
export class QuestionCardComponent {

  question = input.required<Question>();
  actionEdit = output<number>();
  actionDelete = output<number>();

  questionTypeIcons = {
    text: 'fa-solid fa-font',
    number: 'fa-solid fa-hashtag',
    date: 'fa-solid fa-calendar',
    select: 'fa-solid fa-list',
    radio: 'fa-solid fa-list-radio',
    checkbox: 'fa-solid fa-check-square'
  };

  onActionEdit(id : number ):void{
    this.actionEdit.emit(id);
  }
  onActionDelete(id: number):void{
    this.actionDelete.emit(id);
  }
}
