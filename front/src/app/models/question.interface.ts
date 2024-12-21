export interface Question {
  id: number;
  typeQuestion: 'text' | 'number' | 'select' | 'radio' | 'checkbox';
  label: string;
  required: boolean;
  options?: string[];
}
