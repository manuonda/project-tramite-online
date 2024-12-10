import { Component, input } from '@angular/core';
import { Section } from '@models/section.interface';

@Component({
  selector: 'card-section',
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
    section = input.required<Section>();
}
