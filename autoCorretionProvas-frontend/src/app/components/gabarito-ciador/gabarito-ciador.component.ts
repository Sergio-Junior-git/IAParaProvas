import { Component } from '@angular/core';
import { GabaritoService } from '../../services/gabarito.service';


@Component({
  selector: 'app-gabarito-ciador',
  standalone: false,
  templateUrl: './gabarito-ciador.component.html',
  styleUrl: './gabarito-ciador.component.css'
})
export class GabaritoCiadorComponent {
  numQuestions = 5;
  numAlternatives = 5; 
  gabarito: string[] = [];

  constructor(private service: GabaritoService) {
  }
  gerarGabarito() {
    this.gabarito = new Array(this.numQuestions).fill('');
  }

  salvarTemplate() {
    const template = {
    totalQuestoes: this.numQuestions,
    totalAlternativas: this.numAlternatives,
    respostasCorretas: this.gabarito
    };

    this.service.uploadTemplate(template).subscribe ({
    next: (response) => alert('Gabarito salvo com sucesso! ID: ' + response.id),
    error: (error) => alert('Erro ao salvar gabarito: ' + error.message)
    });
  }

  getAlternatives(): string[] {
  return Array.from({ length: this.numAlternatives }, (_, i) =>
    String.fromCharCode(65 + i)
  );
}
}
