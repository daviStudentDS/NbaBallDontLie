namespace BallDo.Models
{
    public class CoachDTO
    {
       
        public string Name { get; set; }
        // Outras propriedades do treinador que você deseja permitir na criação
        public int? TeamId { get; set; } // ID do time (opcional)
    }

}
