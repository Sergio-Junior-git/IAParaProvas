import { Component } from '@angular/core';
import { GabaritoService } from '../../services/gabarito.service';

@Component({
  selector: 'app-gabarito-avaliar',
  standalone: false,
  templateUrl: './gabarito-avaliar.component.html',
  styleUrl: './gabarito-avaliar.component.css'
})
export class GabaritoAvaliarComponent {
  selectedFile?: File;
  templateId = 1;
  nota?: number;

  constructor(private service: GabaritoService) {
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  enviarProva() {
    if (!this.selectedFile) {
      alert('Por favor, selecione um arquivo de prova.');
      return;
    }
    
    this.service.avaliarProva(this.selectedFile, this.templateId).subscribe({
      next: (response) => {
        this.nota = response.nota;
      }
    });
  }
}