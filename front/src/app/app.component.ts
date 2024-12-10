import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SectionsComponent } from '@features/sections';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SectionsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'front';
}
