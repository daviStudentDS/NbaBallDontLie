using Microsoft.EntityFrameworkCore;
using BallDo.Models;

namespace BallDo.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options) { }

        public DbSet<Coach> Coachies { get; set; }
        public DbSet<Player> Players { get; set; }
        public DbSet<Team> Teams { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Player>()
                .HasOne(a => a.Team)
                .WithMany(b => b.Players)
                .HasForeignKey(a => a.TeamId);

            modelBuilder.Entity<Coach>()
                .HasOne(a => a.Team)
                .WithOne(b => b.Coach)
                .HasForeignKey<Team>(a => a.CoachId);
        }
    }
}

        // modelBuilder.Entity<PokeTeamModel>()
        //     .HasOne(a => a.User)
        //     .WithMany(b => b.PokeTeams)
        //     .HasForeignKey(a => a.UserId);

        // modelBuilder.Entity<FavoritePokemonModel>()
        //     .HasOne(a => a.User)
        //     .WithMany(b => b.FavoritePokemons)
        //     .HasForeignKey(a => a.UserId);
    