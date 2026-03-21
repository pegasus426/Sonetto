import { ChangeDetectionStrategy, Component, signal } from '@angular/core';

@Component({
  selector: 'app-root',
  standalone: true,
  template: `
    <main>
      <h1>Sonetto FE</h1>
      <p>Angular bootstrap attivo.</p>
      <button type="button" (click)="increment()">Count: {{ count() }}</button>
    </main>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {
  readonly count = signal<number>(0);

  increment(): void {
    this.count.update((value: number) => value + 1);
  }
}
