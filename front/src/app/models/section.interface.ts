import { Question } from "./question.interface";

export interface Section {
  id: number | null;
  title: string;
  description: string;
  enabled: boolean;
  sectionType: string;
  questions: Question[]
}
