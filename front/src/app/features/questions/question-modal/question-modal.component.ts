import { booleanAttribute, Component , input, linkedSignal, output } from '@angular/core';

@Component({
  selector: 'question-modal',
  imports: [],
  templateUrl: './question-modal.component.html',
  styleUrl: './question-modal.component.scss'
})



export class QuestionModalComponent {


  isOpen = input(false,{ transform: booleanAttribute});
  close = output<boolean>();
  _isOpen = linkedSignal(() => this.isOpen());
  onClose(){
    this._isOpen.update( value => !value);
    console.log("this.isOpent : ", this.isOpen());
    this.close.emit(!this.isOpen());
  }



}
